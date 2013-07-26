geomatico.projectPanel = function() {
   var divId = 'project-panel';
   return {
      init : function() {
         var parentDiv = $("<div>").attr('id', divId);
         $('body').append(parentDiv);
         $(document).bind(
            'projects-received',
            function(event, projects) {
               parentDiv.empty();
               $.each(projects, function(index, project) {
                  var div = $("<div>").html(
                     project.name + ": " + project.description);
                  parentDiv.append(div);
               });
            });
      }
   };
};