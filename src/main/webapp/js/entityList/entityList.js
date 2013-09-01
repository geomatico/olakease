geomatico.entityList = function() {
   var divClass = "entity-list";
   return {
      init : function(parentDiv, title, entityName, entityPath, renderer) {
         var div = $("<div>").addClass(divClass);
         parentDiv.append($("<div>").html(title));
         $(document).bind(
            "get-received",
            function(event, receivedEntityPath, items) {
               if (receivedEntityPath == entityPath) {
                  div.empty();
                  $.each(items, function(index, item) {
                     geomatico.entityLine().init(entityPath + "_" + index, div,
                        entityPath, item, renderer);
                  });
                  geomatico.newLine()
                     .init(div, entityPath, "New " + entityName);
               }
            });
         parentDiv.append(div);
         
         return this;
      }
   };
};