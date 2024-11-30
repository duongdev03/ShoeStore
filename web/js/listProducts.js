
const btnAdd = document.querySelector('.js-addBS');
const modalAdd = document.querySelector(".js-modal-add");
const modalAddClose = document.querySelector('.js-modal-add-close');
const modalAddContainer = document.querySelector('.js-modal-add-container');

function showAdd() {
    modalAdd.classList.add('open');
}

function hideAdd() {
    modalAdd.classList.remove('open');
}

btnAdd.addEventListener('click', showAdd);

modalAddClose.addEventListener('click', hideAdd);

modalAdd.addEventListener('click', hideAdd);

modalAddContainer.addEventListener('click', function (event) {
    event.stopPropagation();
});



