geomatico.newLine = function() {
   var divClass = "entity-line";
   var selectedDivClass = "selected-entity-line";

   var div = null;
   var btnSave = null;
   var btnCancel = null;
   var readOnlyDiv = null;
   var readWriteDiv = null;
   return {
      edit : function() {
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
      init : function(index, parent, entityPath, text, fields) {
         var this_ = this;
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
                     + "' type='text' value=''/>"));
            }
            div.append(readWriteDiv);
         }
         {
            btnSave = $("<div>").html("SAVE").addClass("entity-button").hide();
            div.append(btnSave);
            btnSave.click(function(event) {
               var entity = {};
               for ( var f in fields) {
                  entity[fields[f]] = $("#" + index + "_" + fields[f]).val();
               }
               this_.unselected();
               $(document).trigger("post", [ entityPath, entity ]);
               event.stopPropagation();
            });
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
            this_.edit();
         });
      }
   };
};