/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat Inc., and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.as.weld.ejb;

import org.jboss.ejb.client.SessionID;
import org.jboss.msc.service.ServiceName;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Map;

/**
 * Serialized form of a SFSB
 *
 * @author Stuart Douglas
 */
public class SerializedStatefulSessionObject implements Serializable {

    private final ServiceName componentServiceName;
    private final SessionID sessionID;
    private final Map<Class<?>, ServiceName> serviceNames;

    public SerializedStatefulSessionObject(final ServiceName componentServiceName, final SessionID sessionID, final Map<Class<?>, ServiceName> serviceNames) {
        this.componentServiceName = componentServiceName;
        this.sessionID = sessionID;
        this.serviceNames = serviceNames;
    }

    private Object readResolve() throws ObjectStreamException {
        return new StatefulSessionObjectReferenceImpl(sessionID, componentServiceName, serviceNames);
    }

    public ServiceName getComponentServiceName() {
        return this.componentServiceName;
    }

    public SessionID getSessionID() {
        return this.sessionID;
    }

    public Map<Class<?>, ServiceName> getServiceNames() {
        return this.serviceNames;
    }
}
