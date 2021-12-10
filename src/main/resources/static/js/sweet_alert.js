
export function swalSuccess(message){
    swal.fire({
        position: 'center',
        icon: 'success',
        title: message,
        showConfirmButton: false,
        timer: 2500
    })
}
export  function swalError(message){
    swal.fire({
        position: 'center',
        icon: 'error',
        title: message ? message : "Нещо се обърка!",
        showConfirmButton: false,
        timer: 2500
    })
}