geomatico.entityList = function() {
   var divClass = "entity-list";
   return {
      init : function(title, entityName, eventPrefix, renderer) {
         var div = $("<div>").addClass(divClass);
         $("body").append($("<div>").html(title));
         $(document).bind(
            eventPrefix + "-received",
            function(event, items) {
               div.empty();
               $.each(items,
                  function(index, item) {
                     geomatico.entityLine().init(div, eventPrefix, item,
                        renderer);
                  });
               var newEntityDiv = $("<div>").addClass("entity-line").html(
                  "New " + entityName + "...");
               newEntityDiv.click(function() {
                  $(document).trigger(eventPrefix + "-new");
               });
               div.append(newEntityDiv);
            });
         $("body").append(div);
      }
   };
};