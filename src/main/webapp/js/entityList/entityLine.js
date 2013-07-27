geomatico.entityLine = function() {
   var divClass = "entity-line";
   var selectedDivClass = "selected-entity-line";

   var div = null;
   var btnModify = null;
   var btnSave = null;
   var btnCancel = null;
   var readOnlyDiv = null;
   var readWriteDiv = null;
   return {
      edit : function() {
         readOnlyDiv.hide();
         btnModify.hide();
         readWriteDiv.show();
         btnCancel.show();
         btnSave.show();
      },
      selected : function() {
         div.addClass(selectedDivClass);
         readOnlyDiv.show();
         btnModify.show();
         readWriteDiv.hide();
         btnCancel.hide();
         btnSave.hide();
      },
      unselected : function() {
         div.removeClass(selectedDivClass);
         readOnlyDiv.show();
         btnModify.hide();
         readWriteDiv.hide();
         btnCancel.hide();
         btnSave.hide();
      },
      init : function(index, parent, entityPath, entity, fields) {
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
            readWriteDiv = $("<div>").addClass("entity-content").hide();
            for ( var f in fields) {
               readWriteDiv.append(fields[f] + ":").append(
                  $("<input id='" + index + "_" + fields[f]
                     + "' type='text' value='" + entity[fields[f]] + "'/>"));
            }
            div.append(readWriteDiv);
         }
         {
            btnModify = $("<div>").html("MODIFY").addClass("entity-button")
               .hide();
            div.append(btnModify);
            btnModify.click(function(event) {
               $(document).trigger(entityPath + "-toModify", entity);
               event.stopPropagation();
            });
         }
         {
            btnSave = $("<div>").html("SAVE").addClass("entity-button").hide();
            div.append(btnSave);
            btnSave.click(function(event) {
               for ( var f in fields) {
                  entity[fields[f]] = $("#" + index + "_" + fields[f]).val();
               }
               this_.selected();
               $(document).trigger("put", [ entityPath, entity ]);
               event.stopPropagation();
            });
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
         $(document).bind(entityPath + "-toModify",
            function(event, selectedEntity) {
               if (entity == selectedEntity) {
                  this_.edit();
               }
            });
      }
   };
};