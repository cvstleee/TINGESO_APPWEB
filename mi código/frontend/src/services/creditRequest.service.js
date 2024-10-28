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

const updateStatus = data => {
    return httpClient.put('/creditRequest/status', data);
}

const totalCost = data => {
    return httpClient.put(`/creditRequest/calculateTotalCost/${id}`, data);
}

export default { getAll, create, get, update, remove, totalCost, updateStatus };