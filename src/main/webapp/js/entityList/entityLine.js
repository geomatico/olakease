geomatico.entityLine = function() {
   var divClass = "entity-line";
   var selectedDivClass = "selected-entity-line";
   return {
      init : function(parent, eventPrefix, entity, renderer) {
         var div = $("<div>").addClass(divClass).html(renderer(entity));
         div.click(function() {
            $(document).trigger(eventPrefix + "-selected", entity);
         });
         $(document).bind(eventPrefix + "-selected",
            function(event, selectedEntity) {
               if (entity.id == selectedEntity.id) {
                  div.addClass(selectedDivClass);
               } else {
                  div.removeClass(selectedDivClass);
               }
            });
         parent.append(div);
      }
   };
};