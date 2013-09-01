geomatico.entityLine = function() {
   var divClass = "entity-line";

   var div = null;
   var btnDelete = null;
   var readOnlyDiv = null;
   return {
      init : function(lineIndex, parent, entityPath, entity, fields) {
         var text = "";
         var separator = "";
         for ( var i in fields) {
            text += separator + entity[fields[i]];
            separator = ":";
         }
         div = $("<div>").addClass(divClass);
         {
            readOnlyDiv = $("<div>").addClass("entity-content").html(text);
            div.append(readOnlyDiv);
         }
         {
            btnDelete = $("<div>").html("DELETE").addClass("entity-button");
            div.append(btnDelete);
            btnDelete.click(function(event) {
               $(document).trigger("delete",
                  [ entityPath, entityPath + "/" + entity.id ]);
               event.stopPropagation();
            });
         }
         parent.append(div);

         div.click(function() {
            $(document).trigger(entityPath + "-selected", entity);
         });
      }
   };
};
