const $btnVuelos = document.getElementById('vuelos');
const $btnHoteles = document.getElementById('hoteles');
const $entities = document.getElementById('entities');

const URL = 'http://localhost:8080/api';

$btnVuelos.addEventListener('click', async () => {
    const response = await fetch(`${URL}/vuelos`, {
        method: 'GET'
    });
    if (response.status !== 200) {
        throw new Error('No se pudieron obtener los vuelos');
    }

    const vuelos = await response.json();

    clearEntities();

    const documentFragment = document.createDocumentFragment();
    let article, idP, companiaP, fechaP, precioP, plazasDisponiblesP;
    vuelos.forEach(({
        id,
        compania,
        fecha,
        precio,
        plazasDisponibles
    }) => {
        article = document.createElement('article');
        idP = document.createElement('p');
        companiaP = document.createElement('p');
        fechaP = document.createElement('p');
        precioP = document.createElement('p');
        plazasDisponiblesP = document.createElement('p');

        article.classList.add('entity');
        idP.textContent = `ID: ${id}`;
        idP.classList.add('bold');
        companiaP.textContent = `Compañía: ${compania}`;
        companiaP.classList.add('bold');
        fechaP.textContent = `Fecha: ${fecha}`;
        fechaP.classList.add('bold');
        precioP.textContent = `Precio: ${precio} €`;
        precioP.classList.add('bold');
        plazasDisponiblesP.textContent = `Plazas disponibles: ${plazasDisponibles}`;
        plazasDisponiblesP.classList.add('bold');
        article.appendChild(idP);
        article.appendChild(companiaP);
        article.appendChild(fechaP);
        article.appendChild(precioP);
        article.appendChild(plazasDisponiblesP);
        documentFragment.appendChild(article);
    });

    $entities.appendChild(documentFragment);
});

$btnHoteles.addEventListener('click', async () => {
    const response = await fetch(`${URL}/hoteles`, {
        method: 'GET'
    });
    if (response.status !== 200) {
        throw new Error('No se pudieron obtener los hoteles');
    }

    const hoteles = await response.json();

    clearEntities();

    const documentFragment = document.createDocumentFragment();
    let article, idP, nombreP, categoriaP, precioP, disponibilidadContainer, disponibilidadP, disponibilidadInput;
    hoteles.forEach(({
        id,
        nombre,
        categoria,
        precio,
        disponibilidad
    }) => {
        article = document.createElement('article');
        idP = document.createElement('p');
        nombreP = document.createElement('p');
        categoriaP = document.createElement('p');
        precioP = document.createElement('p');
        disponibilidadContainer = document.createElement('div');
        disponibilidadP = document.createElement('p');
        disponibilidadInput = document.createElement('input');

        article.classList.add('entity');
        idP.textContent = `ID: ${id}`;
        idP.classList.add('bold');
        nombreP.textContent = `Nombre: ${nombre}`;
        nombreP.classList.add('bold');
        categoriaP.textContent = `Categoría: ${categoria}`;
        categoriaP.classList.add('bold');
        precioP.textContent = `Precio: ${precio} €`;
        precioP.classList.add('bold');
        disponibilidadContainer.classList.add('row');
        disponibilidadP.textContent = 'Disponibilidad: ';
        disponibilidadP.classList.add('bold');
        disponibilidadInput.type = 'checkbox';
        disponibilidadInput.checked = disponibilidad;

        disponibilidadContainer.appendChild(disponibilidadP);
        disponibilidadContainer.appendChild(disponibilidadInput);

        article.appendChild(idP);
        article.appendChild(nombreP);
        article.appendChild(categoriaP);
        article.appendChild(precioP);
        article.appendChild(disponibilidadContainer);
        documentFragment.appendChild(article);
    });

    $entities.appendChild(documentFragment);
});

function clearEntities() {
    while ($entities.firstChild) {
        $entities.firstChild.remove();
    }
}