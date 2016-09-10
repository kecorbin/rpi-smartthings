# rpi-smartthings

Use RPI GPIO sensors as ST devices (contact/motion) I found a lot of samples of using RPI for controlling relays/switches and such, but 
nothing that just did plain old input sensors.  With minor modification, this can be used to integrate any digitalIn Pi sensor.  

Hopefully others find this useful.  

Based upon work by @Ibeech [https://github.com/iBeech/SmartThings](https://github.com/iBeech/SmartThings)

## Requirements
* Smartthings Hub
* Raspberry Pi
* Various Contact/Motion Sensors supported.  

## Usage

1. Install device type handlers
2. Install WebIOpi SmartApp
3. Publish WebIOpi SmartApp
4. Add SmartApp from the Smarthings Mobile App
