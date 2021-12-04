const token = $("input[name='_csrf']").val()
const header = $("meta[name='_csrf_header']").attr("content");


export async function ajaxDelete(url){
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
            swal.fire({
                position: 'top-end',
                icon: 'success',
                title: 'Deleted',
                showConfirmButton: false,
                timer: 2000
            })
        },
        error : function(e) {
            alert("Error!")
        }
    })
}