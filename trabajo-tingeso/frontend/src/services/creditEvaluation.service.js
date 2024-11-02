import httpClient from '../http-common';

const getAll = () => {
    return httpClient.get('/creditEvaluation/');
}

const create = data => {
    return httpClient.post("/creditEvaluation/", data);
}

const get = id => {
    return httpClient.get(`/creditEvaluation/${id}`);
}

const update = data => {
    return httpClient.put('/creditEvaluation/', data);
}

const remove = id => {
    return httpClient.delete(`/creditEvaluation/${id}`);
}

export default { getAll, create, get, update, remove};