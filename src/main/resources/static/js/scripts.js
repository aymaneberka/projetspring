$(document).ready(function () {
    $(".btn-delete").on("click", function (e) {
        e.preventDefault();  // Empêche la navigation

        let articleId = $(this).attr("data-id");
        let articledescription = $(this).attr("data-description");

        $("#confirmText").html("Do you want to delete <strong>" + articledescription + "</strong>?");
        $("#yesBtn").attr("data-id", articleId);  // Stocke l'ID de la personne
        $("#confirmModal").modal("show");  // Affiche la modale
    });

    $("#yesBtn").on("click", function () {
        let articleId = $(this).attr("data-id");

        $.ajax({
            url: "/articles/delete/" + articleId,
            type: "DELETE",
            success: function (response) {
                $("#confirmModal").modal("hide");  // Ferme la modale
                $("a[data-id='" + articleId + "']").closest("tr").fadeOut();  // Supprime la ligne du tableau
            },
            error: function () {
                alert("Error deleting person");
            }
        });
    });
});
