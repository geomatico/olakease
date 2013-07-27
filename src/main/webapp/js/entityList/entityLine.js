geomatico.entityLine = function() {
   var divClass = "entity-line";
   var selectedDivClass = "selected-entity-line";
   return {
      init : function(index, parent, entityPath, entity, fields) {
         var text = "";
         var separator = "";
         for ( var i in fields) {
            text += separator + entity[fields[i]];
            separator = ":";
         }
         var div = $("<div>").addClass(divClass);
         var readOnlyDiv, readWriteDiv;
         {
            readOnlyDiv = $("<div>").addClass("entity-content").html(text);
            div.append(readOnlyDiv);
         }
         {
            readWriteDiv = $("<div>").addClass("entity-content").hide();
            for ( var f in fields) {
               readWriteDiv.append(fields[f] + ":").append(
                  $("<input id='" + index + "_" + fields[f]
                     + "' type='text' value='" + entity[fields[f]] + "'/>"));
            }
            div.append(readWriteDiv);
         }
         {
            var btnModify = $("<div>").html("MODIFY").addClass("entity-button")
               .hide();
            div.append(btnModify);
            btnModify.click(function() {
               $(document).trigger(entityPath + "-toModify", entity);
            });
         }
         {
            var btnSave = $("<div>").html("SAVE").addClass("entity-button")
               .hide();
            div.append(btnSave);
            btnSave.click(function() {
               for ( var f in fields) {
                  entity[fields[f]] = $("#" + index + "_" + fields[f]).val();
               }
               $(document).trigger("put", [ entityPath, entity ]);
            });
         }
         parent.append(div);

         div.click(function() {
            $(document).trigger(entityPath + "-selected", entity);
         });
         $(document).bind(entityPath + "-selected",
            function(event, selectedEntity) {
               if (entity.id == selectedEntity.id) {
                  div.addClass(selectedDivClass);
                  btnModify.show();
               } else {
                  div.removeClass(selectedDivClass);
                  btnModify.hide();
                  btnSave.hide();
                  readWriteDiv.hide();
                  readOnlyDiv.show();
               }
            });
         $(document).bind(entityPath + "-toModify",
            function(event, selectedEntity) {
               if (entity.id == selectedEntity.id) {
                  readOnlyDiv.hide();
                  readWriteDiv.show();
                  btnModify.hide();
                  btnSave.show();
               }
            });
      }
   };
};