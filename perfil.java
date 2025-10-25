const KEY = 'perfil_jornal';
const nameInput = document.getElementById('name');
const classInput = document.getElementById('class');
const bioInput = document.getElementById('bio');
const photoInput = document.getElementById('photoInput');
const photoPreview = document.getElementById('photoPreview');
const saveBtn = document.getElementById('saveBtn');
const clearBtn = document.getElementById('clearBtn');

// Carrega perfil ao abrir a página
document.addEventListener('DOMContentLoaded', () => {
  const data = localStorage.getItem(KEY);
  if (data) {
    const profile = JSON.parse(data);
    nameInput.value = profile.name || '';
    classInput.value = profile.class || '';
    bioInput.value = profile.bio || '';
    if (profile.photo) {
      photoPreview.src = profile.photo;
      photoPreview.style.display = 'block';
    }
  }
});

// Preview da foto
photoInput.addEventListener('change', () => {
  const file = photoInput.files[0];
  if (!file) return;
  const reader = new FileReader();
  reader.onload = e => {
    photoPreview.src = e.target.result;
    photoPreview.style.display = 'block';
  };
  reader.readAsDataURL(file);
});

// Salvar perfil
saveBtn.addEventListener('click', () => {
  const profile = {
    name: nameInput.value.trim(),
    class: classInput.value.trim(),
    bio: bioInput.value.trim(),
    photo: photoPreview.src || ''
  };
  if (!profile.name) { alert('Digite seu nome!'); return; }
  localStorage.setItem(KEY, JSON.stringify(profile));
  alert('Perfil salvo!');
});

// Limpar perfil
clearBtn.addEventListener('click', () => {
  if (confirm('Deseja apagar o perfil?')) {
    localStorage.removeItem(KEY);
    nameInput.value = '';
    classInput.value = '';
    bioInput.value = '';
    photoPreview.src = '';
    photoPreview.style.display = 'none';
  }
});