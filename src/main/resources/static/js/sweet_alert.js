
export function swalSuccess(message){
    swal.fire({
        position: 'center',
        icon: 'success',
        title: message,
        showConfirmButton: false,
        timer: 2500
    })
}
export  function swalError(){
    swal.fire({
        position: 'center',
        icon: 'error',
        title: "Sorry something went wrong",
        showConfirmButton: false,
        timer: 2500
    })
}