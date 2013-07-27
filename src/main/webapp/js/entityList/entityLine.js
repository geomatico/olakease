geomatico.createReadWriteDiv = function(fields, index, getInitValues) {
   var div = $("<div>").addClass("entity-content").hide();
   for ( var f in fields) {
      div.append(fields[f] + ":").append(
         $("<input id='" + index + "_" + fields[f] + "' type='text' value='"
            + getInitValues(fields[f]) + "'/>"));
   }

   return div;
};
geomatico.createSaveButton = function(fields, sourceEntity, entityPath, index,
   restMethod, line) {
   var btn = $("<div>").html("SAVE").addClass("entity-button").hide();
   btn.click(function(event) {
      for ( var f in fields) {
         sourceEntity[fields[f]] = $("#" + index + "_" + fields[f]).val();
      }
      line.selected();
      $(document).trigger(restMethod, [ entityPath, sourceEntity ]);
      event.stopPropagation();
   });
   return btn;
};
geomatico.entityLine = function() {
   var divClass = "entity-line";
   var selectedDivClass = "selected-entity-line";

   var div = null;
   var btnModify = null;
   var btnDelete = null;
   var btnSave = null;
   var btnCancel = null;
   var readOnlyDiv = null;
   var readWriteDiv = null;
   return {
      edit : function() {
         readOnlyDiv.hide();
         btnModify.hide();
         btnDelete.hide();
         readWriteDiv.show();
         btnCancel.show();
         btnSave.show();
      },
      selected : function() {
         div.addClass(selectedDivClass);
         readOnlyDiv.show();
         btnModify.show();
         btnDelete.show();
         readWriteDiv.hide();
         btnCancel.hide();
         btnSave.hide();
      },
      unselected : function() {
         div.removeClass(selectedDivClass);
         readOnlyDiv.show();
         btnModify.hide();
         btnDelete.hide();
         readWriteDiv.hide();
         btnCancel.hide();
         btnSave.hide();
      },
      init : function(lineIndex, parent, entityPath, entity, fields) {
         var this_ = this;
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
            readWriteDiv = geomatico.createReadWriteDiv(fields, lineIndex,
               function(key) {
                  return entity[key];
               });
            div.append(readWriteDiv);
         }
         {
            btnModify = $("<div>").html("MODIFY").addClass("entity-button")
               .hide();
            div.append(btnModify);
            btnModify.click(function(event) {
               this_.edit();
               event.stopPropagation();
            });
         }
         {
            btnDelete = $("<div>").html("DELETE").addClass("entity-button")
               .hide();
            div.append(btnDelete);
            btnDelete.click(function(event) {
               $(document).trigger("delete", [ entityPath, entityPath + "/" + entity.id ]);
               event.stopPropagation();
            });
         }
         {
            btnSave = geomatico.createSaveButton(fields, entity, entityPath,
               lineIndex, "put", this_);
            div.append(btnSave);
         }
         {
            btnCancel = $("<div>").html("CANCEL").addClass("entity-button")
               .hide();
            div.append(btnCancel);
            btnCancel.click(function(event) {
               this_.selected();
               event.stopPropagation();
            });
         }
         parent.append(div);

         div.click(function() {
            if (!div.hasClass(selectedDivClass)) {
               $(document).trigger(entityPath + "-selected", entity);
            }
         });
         $(document).bind(entityPath + "-selected",
            function(event, selectedEntity) {
               if (entity == selectedEntity) {
                  this_.selected();
               } else {
                  this_.unselected();
               }
            });
      }
   };
};
