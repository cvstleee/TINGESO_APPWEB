import httpClient from '../http-common';

const getAll = () => {
    return httpClient.get('/creditRequest/');
}

const create = data => {
    return httpClient.post("/creditRequest/", data);
}

const get = id => {
    return httpClient.get(`/creditRequest/${id}`);
}

const update = data => {
    return httpClient.put('/creditRequest/', data);
}

const remove = id => {
    return httpClient.delete(`/creditRequest/${id}`);
}
export default { getAll, create, get, update, remove };