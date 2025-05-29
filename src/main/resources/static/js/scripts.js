// Affiche la modale de confirmation
$(document).on("click", ".btn-delete", function (e) {
    e.preventDefault();

    const articleId = $(this).data("id");
    const articleDescription = $(this).data("description");

    $("#confirmText").html("Voulez-vous supprimer <strong>" + articleDescription + "</strong> ?");
    $("#yesBtn").data("id", articleId);

    const confirmModal = new bootstrap.Modal(document.getElementById("confirmModal"));
    confirmModal.show();
});

// Envoie la requête DELETE
$("#yesBtn").on("click", function () {
    const articleId = $(this).data("id");
    const confirmModal = bootstrap.Modal.getInstance(document.getElementById("confirmModal"));

    $.ajax({
        url: "/articles/delete/" + articleId,
        type: "DELETE",
        success: function () {
            confirmModal.hide();
            $("button[data-id='" + articleId + "']").closest("tr").fadeOut();
        },
        error: function () {
            alert("Erreur lors de la suppression.");
        }
    });
});
