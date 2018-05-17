function Validate(){
    var validForm = true;
    $('input').each(function() {
        validForm = validForm && this.checkValidity();
    });
    return validForm;
}

function SuccessChange(data) {
    var parseData = JSON.parse(data);
    if (parseData.error == ""){
        document.location.href = document.location.protocol + "//" + document.location.host + parseData.nextPage
    }
    else{
        confirm(parseData.error);
    }
}

function ErrorChange(data) {
    console.log("error");
    console.log(data);
}

function SendAction(actionType){
    var locationURL = document.location.protocol + "//" + document.location.host + GetEditUrl();
    $.ajax({
        url: locationURL,
        type: actionType,
        contentType: "application/json",
        data: JSON.stringify(GetData()),
        success: SuccessChange,
        error: ErrorChange
    });
}

function Create(){
    if (Validate()){
        SendAction("POST");
    }
}

function Delete() {
    SendAction("DELETE");
}

function Save() {
    if (Validate()){
        SendAction("PUT");
    }
}

var urlGlobal = "";
var itemGlobal = "";

function DeleteEntities(url, item) {
    urlGlobal = url;
    itemGlobal = item;
    DeleteNextEntity("");
}

function DeleteNextEntity(data) {
    var entity = $('#'+itemGlobal+' input:checked:first');

    if (entity.val() != undefined){
        entity.prop('checked', false);
        DeleteEntity(entity.val());
    }else {
        location.reload();
    }
}

function DeleteEntity(id){
    var locationURL = document.location.protocol + "//" + document.location.host + urlGlobal + "?id=" + id;
    $.ajax({
        url: locationURL,
        type: "DELETE",
        contentType: "application/json",
        success: DeleteNextEntity,
        error: DeleteNextEntity
    });
}