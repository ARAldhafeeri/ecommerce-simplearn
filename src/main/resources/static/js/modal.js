const createModal = document.getElementById('createModal');

const product = {
    name: '',
    price: '',
    dateAdded: '',
    categoryId: ''
}
// Function to open the edit modal
function openEditModal(id) {
   let editModal = document.querySelector(`[dataid='${id}']`);
    editModal.style.display = 'block';
}

// Function to close the edit modal
function closeEditModal(id) {
    let editModal = document.querySelector(`[dataid='${id}']`);
    editModal.style.display = 'none';
}

// Function to open the delete modal
function openDeleteModal(id) {
    let deleteModal = document.querySelector(`[dataid-delete='${id}']`);
    deleteModal.style.display = 'block';
}

// Function to close the delete modal
function closeDeleteModal(id) {
    let deleteModal = document.querySelector(`[dataid-delete='${id}']`);
    deleteModal.style.display = 'none';
}

// Function to open the create modal
function openCreateModal() {
    createModal.style.display = 'block';
}

// Function to close the create modal
function closeCreateModal() {
    createModal.style.display = 'none';
}