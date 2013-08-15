geomatico.newLine = function() {
   var divClass = "entity-line";

   var div = null;
   return {
      init : function(parent, entityPath, text) {
         div = $("<div>").addClass(divClass).html(text);
         parent.append(div);

         div.click(function() {
            $(document).trigger(entityPath + "-new");
         });
      }
   };
};