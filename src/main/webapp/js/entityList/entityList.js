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
               geomatico.newLine().init(entityPath + "_new", div,
                  entityPath, "New " + entityName, renderer);
            });
         $("body").append(div);
      }
   };
};