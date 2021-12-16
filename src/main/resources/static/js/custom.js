import {ajaxDelete, ajaxGet, ajaxPost} from "./ajax.js";
import {swalError, swalSuccess} from "./sweet_alert.js";


$(document).ready(function () {
    //ADMIN CATEGORIES
    //add category field
    $('.js-add-field').click(function () {
        let fieldsHolder = $('.js-fields-holder-disabled ').clone();
        fieldsHolder.removeClass("d-none js-fields-holder-disabled").addClass("d-flex")
        fieldsHolder.find('.form-control').each((index, field) => {
            $(field).removeAttr('disabled')
        })
        $('.fields-container').append(fieldsHolder)
    })

    //remove category field
    $(document).on('click', '.js-remove-field', function () {
        let fieldId = $(this).data('category_field_id')
        if (fieldId) {
            confirmAndDelete("/admin/category-fields/" + fieldId)
            $(this).parents("tr").remove()

        }
        $(this).parents('.js-fields-holder').remove()
    })

    //END ADMIN CATEGORIES

    //ADMIN USERS

    $('.js-users-del').on('click', function () {
        let userId = $(this).data('user_id')

        Swal.fire({
            title: 'Сигурен ли си?',
            text: "Този потребител ще бъде изтрит.",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            cancelButtonText: 'Отказ',
            confirmButtonText: 'Изтрии'
        }).then((result) => {
            if (result.isConfirmed) {
                ajaxPost("/admin/users/" + userId + "/delete", {})
                $(this).parents("tr").remove()
            }
        })
    })

    //END ADMIN USERS

    //ADMIN CITIEs

    $('.js-city_del').on('click', function () {
        let cityId = $(this).data('city_id')
        confirmAndDelete("/admin/cities/" + cityId)
        $(this).parents("tr").remove()

    })

    //END CITIES


    //OBJECTS
    //GET FIELDS BY CATEGORY
    $('.js-object-category').on('change', function () {
        let categorySlug = $(this).find('option:selected').val();
        let fieldsContainer = $('.js-object-fields');
        fieldsContainer.find('.js-object-field').remove();

        ajaxGet('/api/category-fields?category_slug=' + categorySlug, function (response) {
            $.each(response, function (i, field) {
                let fieldHolder = getCategoryFieldInput(field)
                fieldsContainer.append(fieldHolder)
            })
        })
        $(this).find('.js-not-selected-option').attr('disabled', 'disabled');
        $('.js-object-create-form').find('input[type="submit"]').removeAttr('disabled')
    })
    //CREATE OBJECT VALIDATION
    $('.js-object-create-form').on('submit', function (e) {
        e.preventDefault()
        let error = false;
        if ($(this).find('input#name').val().length < 4) {
            error = true;
        }
        if ($(this).find('textarea#description').val().length < 4) {
            error = true;
        }
        if (error) {
            swalError('Моля попълнете всички полета')
        } else {
            $(this).unbind('submit').submit()
        }

    })

    //ADMIN OBJECTS
    //APPROVE OBJECT
    $('.js-approve-object').on('click', function (e) {
        let btn = $(this)
        let objectId = btn.data('object_id')
        Swal.fire({
            title: 'Сигурен ли си?',
            text: "След като обектът бъде одобрен ще бъде видим за всички потребители",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            cancelButtonText: 'Отказ',
            confirmButtonText: 'Одобри'
        }).then((result) => {
            if (result.isConfirmed) {
                ajaxPost('/api/objects/approve?object_id=' + objectId, {}, (response) => {
                    swalSuccess()
                    btn.parents('tr').remove()
                })
            }
        })
    })

    $('.js-add-comment-form').on('submit', function (e) {
        e.preventDefault()
        if ($(this).find('textarea#content').val().length < 4) {
            swalError('Не може да добавите празен коментар')
        } else {
            $(this).unbind('submit').submit()
        }
    })
    showObjectRating()

    $('.js-object-rating').on('click',function (e){
        let parent = $('.js-rating-holder')
        let canRate = parent.data('can_rate')
        let objectId = parent.data('object_id')
        let rate = $(this).data('rate')
        if (rate ===0){
            swalError('Моля влезте в ситемата за да гласувате')
        }else {
            if (canRate) {
                ajaxPost("/api/rating?object_id="+objectId + '&rate='+rate,{},function (response) {
                    swalSuccess("Вие дадохте оценка "+ rate + " за този обект")
                })
            } else {
                swalError('Вече сте дали рейтинг')
            }
        }
    })

    $('.js-comments-edit-modal-btn').on('click', function (e){
        let commentId = $(this).data('comment_id')
        let content = $(this).data('comment_content').trim()
        $("#editCommentDialog").find('textarea').val(content)
        $('.js-edit-comment').data('comment_id',commentId)
    })

    $('.js-edit-comment').on('click',function (e){
        let commentId = $(this).data('comment_id')
        let content = $(this).parents('.modal-dialog').find('#edit_content').val()

        if (content.length <=4 ){
            swalError("Коментара не може да е по кратък от 4 символа!")
            return;
        }
        ajaxPost("/api/comments?comment_id="+commentId+"&content="+content,{},function (response){
            swalSuccess("Коментарът беше редактиран")
            $('.comment-'+commentId).text(content)
            $("#editCommentDialog").modal('hide')

        })
    })

    //END OBJECTS

    //GENERAL
    $('.js-admin-index-delete-entity-form').on('submit', function (e) {
        e.preventDefault()
        Swal.fire({
            title: 'Сигурен ли си?',
            text: "Наистина ли искаш да изтриеш?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            cancelButtonText: 'Отказ',
            confirmButtonText: 'Изтрии'
        }).then((result) => {
            if (result.isConfirmed) {
                $(this).unbind('submit').submit()
            }
        })
    })


});

function confirmAndDelete(url) {
    Swal.fire({
        title: 'Сигурен ли си?',
        text: "Наистина ли искаш да изтриеш?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        cancelButtonText: 'Отказ',
        confirmButtonText: 'Изтрии'
    }).then((result) => {
        if (result.isConfirmed) {
            ajaxDelete(url)
        }
    })
}

function getCategoryFieldInput(field) {
    let fieldHolder = $('.js-object-field-hidden').clone()
    fieldHolder.removeClass('js-object-field-hidden d-none').addClass('js-object-field')
    let label = fieldHolder.find('label')
    label.attr('for', field.slug)
    label.text(field.name)
    let inputEl = fieldHolder.find('input')
    inputEl.removeAttr('disabled')
        .attr('name', field.slug)
        .attr('id', field.slug)
        .attr('placeholder', field.name)

    return fieldHolder;
}

function showObjectRating() {
    let ratingHolder = $('.js-rating-holder')
    if (ratingHolder) {
        let rating = ratingHolder.data('average_rating')
        switch (true) {
            case (rating === 0):
                break
            case (rating > 0 && rating <= 1) :
                ratingHolder.find('.js-rate-1').addClass('checked')
                break
            case (rating > 1 && rating <= 2) :
                ratingHolder.find('.js-rate-1').addClass('checked')
                ratingHolder.find('.js-rate-2').addClass('checked')
                break
            case (rating > 2 && rating <= 3) :
                ratingHolder.find('.js-rate-1').addClass('checked')
                ratingHolder.find('.js-rate-2').addClass('checked')
                ratingHolder.find('.js-rate-3').addClass('checked')
                break
            case (rating > 3 && rating <= 4) :
                ratingHolder.find('.js-rate-1').addClass('checked')
                ratingHolder.find('.js-rate-2').addClass('checked')
                ratingHolder.find('.js-rate-3').addClass('checked')
                ratingHolder.find('.js-rate-4').addClass('checked')
                break
            case (rating > 4 && rating <= 5) :
                ratingHolder.find('.js-rate-1').addClass('checked')
                ratingHolder.find('.js-rate-2').addClass('checked')
                ratingHolder.find('.js-rate-3').addClass('checked')
                ratingHolder.find('.js-rate-4').addClass('checked')
                ratingHolder.find('.js-rate-5').addClass('checked')
                break
        }
    }
}


