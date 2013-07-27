geomatico.entityList = function() {
   var divClass = "entity-list";
   return {
      init : function(title, entityName, entityPath, renderer) {
         var div = $("<div>").addClass(divClass);
         $("body").append($("<div>").html(title));
         $(document).bind(
            entityPath + "-received",
            function(event, items) {
               div.empty();
               $.each(items, function(index, item) {
                  geomatico.entityLine().init(entityPath + "_" + index, div,
                     entityPath, item, renderer);
               });
               var newEntityDiv = $("<div>").addClass("entity-line").html(
                  "New " + entityName + "...");
               newEntityDiv.click(function() {
                  $(document).trigger(entityPath + "-new");
               });
               div.append(newEntityDiv);
            });
         $("body").append(div);
      }
   };
};