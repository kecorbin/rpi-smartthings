/**
 *  Pi Contact Sensor
 *
 *  Copyright 2016 Kevin Corbin
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
 
metadata {
	definition (name: "Pi Contact Sensor", namespace: "smartthings", author: "Kevin Corbin") {
		capability "Contact Sensor"
        capability "Refresh"
		capability "Polling"
        
        command "changeSensorState", ["string"]
	}

	simulator {
		// TODO: define status and reply messages here
	}

	tiles(scale: 2) {
		standardTile("contact", "device.contact", width: 6, height: 4) {
			state("closed", label:'closed', icon:"st.contact.contact.closed", backgroundColor:"#79b821")
			state("open", label:'open', icon:"st.contact.contact.open", backgroundColor:"#ff0000")
		}
        
        standardTile("refresh", "device.refresh", inactiveLabel: false, decoration: "flat", width: 3, height: 3) {
			state("default", label:'refresh', action:"polling.poll", icon:"st.secondary.refresh-icon")
		}
        
		main "contact"
		details (["contact", "refresh"])
	}
    
}

// parse events into attributes
def parse(String description) {
	// this method is only used when communicating directly to the actual device - in our case, 
    // we have a raspberry pi (webiopi) we are communicating with, not the sensor itself.
    log.debug "SHOULD NOT BE HERE"
}

def poll() {
	log.debug "Executing 'poll'"   
        def lastState = device.currentValue("contact")
    	sendEvent(name: "contact", value: device.deviceNetworkId + ".refresh")
}

def refresh() {
	log.debug "Executing 'refresh'" 
	poll();
}

def changeSensorState(newState) {

	log.trace "Received update that this sensor is now $newState"
	switch(newState) {
    	case 1:
        	log.trace 'handling case 1'
			sendEvent(name: "contact", value: "open")
            break;
    	case 0:
        	log.trace 'handling case 0'
        	sendEvent(name: "contact", value: "closed")
            break;
    }
}