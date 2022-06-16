import request from '@/config/interceptor.js'

/**
 * Simple RESTful resource class
 */

class Resource {
    constructor(uri) {
        this.uri = uri;
    }
    list(query) {
        return request({
            url: '/' + this.uri,
            method: 'get',
            params: query,
        });
    }

    get(id) {
        return request({
            url: '/' + this.uri + '/' + id,
            method: 'get',
        });
    }

    post(resource) {
        return request({
            url: '/' + this.uri,
            method: 'post',
            data: resource,
        });
    }

    put(id, resource) {
        return request({
            url: '/' + this.uri + '/' + id,
            method: 'put',
            data: resource,
        });
    }

    putOnlyResource(resource) {
        return request({
            url: '/' + this.uri,
            method: 'put',
            data: resource,
        });
    }

    delete(id) {
        return request({
            url: '/' + this.uri + '/' + id,
            method: 'delete',
        });
    }

}

export { Resource as default };