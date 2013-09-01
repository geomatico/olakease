geomatico.entityForm = function() {
   return {
      init : function(parentDiv, title, entityPath, entity, fields) {
         var div = $("<div>").addClass("entity-form");
         div.append($("<div>").html(title));
         for ( var f in fields) {
            div.append(fields[f] + ":").append(
               $("<input id='field_" + fields[f] + "' type='text' value='"
                  + entity[fields[f]] + "'/>"));
         }
         parentDiv.append(div);
      }
   };
};