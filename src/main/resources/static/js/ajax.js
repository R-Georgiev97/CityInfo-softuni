import {swalSuccess, swalError} from "./sweet_alert.js";

const token = $("input[name='_csrf']").val()

export function ajaxGet(url, callback) {
    $.ajax({
        type: 'GET',
        url: url,
        xhrFields: {
            withCredentials: true,
        },
        headers: {
            "X-CSRF-TOKEN": token,
            "Content-Type": "application/json"
        },
        success: function (result) {
            callback(result);
        },
        error: function (e) {
            swalError()
        }
    })
}

export function ajaxDelete(url) {
    $.ajax({
        type: 'DELETE',
        url: url,
        xhrFields: {
            withCredentials: true,
        },
        headers: {
            "X-CSRF-TOKEN": token,
            "Content-Type": "application/json"
        },
        success: function (result) {
            swalSuccess("Изтрито")
        },
        error: function (e) {
            swalError()
        }
    })
}

export function ajaxPost(url, data, callback) {
    $.ajax({
        type: 'POST',
        url: url,
        xhrFields: {
            withCredentials: true,
        },
        data: data,
        headers: {
            "X-CSRF-TOKEN": token,
            "Content-Type": "application/json"
        },
        success: callback
            ? callback
            : function (result) {
                swalSuccess("Success")
            },
        error: function (e) {
            swalError()
        }
    })
}