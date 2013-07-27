geomatico.newLine = function() {
   var divClass = "entity-line";
   var selectedDivClass = "selected-entity-line";

   var div = null;
   var btnSave = null;
   var btnCancel = null;
   var readOnlyDiv = null;
   var readWriteDiv = null;
   return {
      selected : function() {
         readOnlyDiv.hide();
         readWriteDiv.show();
         btnCancel.show();
         btnSave.show();
      },
      unselected : function() {
         div.removeClass(selectedDivClass);
         readOnlyDiv.show();
         readWriteDiv.hide();
         btnCancel.hide();
         btnSave.hide();
      },
      init : function(lineIndex, parent, entityPath, text, fields) {
         var this_ = this;
         div = $("<div>").addClass(divClass);
         {
            readOnlyDiv = $("<div>").addClass("entity-content").html(text);
            div.append(readOnlyDiv);
         }
         {
            readWriteDiv = geomatico.createReadWriteDiv(fields, lineIndex,
               function(key) {
                  return '';
               });
            div.append(readWriteDiv);
         }
         {
            btnSave = geomatico.createSaveButton(fields, {}, entityPath,
               lineIndex, "post", this_);
            div.append(btnSave);
         }
         {
            btnCancel = $("<div>").html("CANCEL").addClass("entity-button")
               .hide();
            div.append(btnCancel);
            btnCancel.click(function(event) {
               this_.unselected();
               event.stopPropagation();
            });
         }
         parent.append(div);

         div.click(function() {
            this_.selected();
         });
      }
   };
};