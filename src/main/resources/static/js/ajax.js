import swalSuccess from "./sweet_alert.js";

const token = $("input[name='_csrf']").val()

export function ajaxDelete(url){
    $.ajax({
        type:'DELETE',
        url : url,
        xhrFields: {
            withCredentials: true,
        },
        headers: {
            "X-CSRF-TOKEN" : token,
            "Content-Type": "application/json"
        },
        success : function(result) {
            swalSuccess("Deleted")
        },
        error : function(e) {
            alert("Error!")
        }
    })
}

export function ajaxPost(url, data){
    $.ajax({
        type:'POST',
        url : url,
        xhrFields: {
            withCredentials: true,
        },
        data: data,
        headers: {
            "X-CSRF-TOKEN" : token,
            "Content-Type": "application/json"
        },
        success : function(result) {
            swalSuccess("Success")
        },
        error : function(e) {
            alert("Error!")
        }
    })
}