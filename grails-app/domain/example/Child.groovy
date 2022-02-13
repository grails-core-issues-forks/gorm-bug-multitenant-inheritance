package example

import grails.gorm.MultiTenant
import org.codehaus.groovy.util.HashCodeHelper

class Child extends Parent implements MultiTenant<Child>, Serializable {

    String tenantId
    String description

    static mapping = {
        id generator: 'assigned', composite: ['id', 'tenantId']
    }

    @Override
    boolean equals(other) {
        if (other instanceof Child) {
            return other.id == id && other.tenantId == tenantId
        }
        false
    }

    int hashCode() {
        int hashCode = HashCodeHelper.initHash()
        if (id) {
            hashCode = HashCodeHelper.updateHash(hashCode, id)
        }
        if (tenantId) {
            hashCode = HashCodeHelper.updateHash(hashCode, tenantId)
        }
        hashCode
    }
}
