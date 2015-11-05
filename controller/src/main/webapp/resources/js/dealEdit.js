/**
 * Created by oleg on 10/24/15.
 */
function addContactClick() {
    $("#add_contact_container").show();
    $("#add_contact_btn").val("Сохранить");
}

function cancelAddContactClick() {
    $("#add_contact_container").hide();
    $("#add_contact_btn").val("Добавить контакт");
}

function showEmptyContactClick() {
    var id = this.id.substr("show_empty_fields_contact_".length);

    alert(id);
}

function initContactTabs() {
    $("[name=show_empty_fields_contact]").click(showEmptyContactClick);
    $("#tab_contacts > li > a").first().click();
}


function onResize() {
    return;
    if($(window).width() < '762') {
        $('#block_company_comments').detach().insertAfter('#block_crearfix');
        $('#block_add_task').detach().insertBefore('#block_crearfix');
    } else {
        $('#block_company_comments').detach().insertBefore('#block_crearfix');
        $('#block_add_task').detach().insertAfter('#block_crearfix');
    }
}

function onLoad() {
    initContactTabs();
    $("#add_contact_btn").click(addContactClick);
    $("#cancel_add_contact_btn").click(cancelAddContactClick);
}

$(window).load(onLoad);
$(window).resize(onResize);
