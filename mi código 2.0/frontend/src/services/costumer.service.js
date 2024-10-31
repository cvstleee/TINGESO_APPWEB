import httpClient from '../http-common';

const getAll = () => {
    return httpClient.get('/costumer/');
}

const create = data => {
    return httpClient.post("/costumer/", data);
}

const get = id => {
    return httpClient.get(`/costumer/${id}`);
}

const update = data => {
    return httpClient.put('/costumer/', data);
}

const remove = id => {
    return httpClient.delete(`/costumer/${id}`);
}
export default { getAll, create, get, update, remove };