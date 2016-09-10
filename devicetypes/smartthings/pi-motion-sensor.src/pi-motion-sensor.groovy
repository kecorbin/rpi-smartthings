/**
 *  Pi Motion Sensor
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
	definition (name: "Pi Motion Sensor", namespace: "smartthings", author: "Kevin Corbin") {
		capability "Motion Sensor"
        capability "Refresh"
		capability "Polling"
        
        command "changeSensorState", ["string"]
	}

	simulator {
		// TODO: define status and reply messages here
	}

	// scale: 2  make the detail view a 6x Unlimited grid
    tiles(scale: 2) {
		standardTile("motion", "device.motion", width: 6, height: 4) {
			state("inactive", label:'no motion', icon:"st.motion.motion.inactive", backgroundColor:"#79b821")
			state("active", label:'motion', icon:"st.motion.motion.active", backgroundColor:"#ff0000")
		}
        
        standardTile("refresh", "device.refresh", inactiveLabel: false, decoration: "flat", width: 3, height: 3) {
			state("default", label:'refresh', action:"polling.poll", icon:"st.secondary.refresh-icon")
		}
        
        // Title to be shown in the "things" view
		main "motion"
        // List of tiles shown in the detail view
		details (["motion", "refresh"])
	}
    
}

// parse events into attributes
def parse(String description) {
	log.debug "Virtual siwtch parsing '${description}'"
}

def poll() {
	log.debug "Executing 'poll'"   
        def lastState = device.currentValue("motion")
    	sendEvent(name: "motion", value: device.deviceNetworkId + ".refresh")
        // sendEvent(name: "motion", value: lastState);
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
			sendEvent(name: "motion", value: "active")
            break;
    	case 0:
        	log.trace 'handling case 0'
        	sendEvent(name: "motion", value: "inactive")
            break;
    }
}