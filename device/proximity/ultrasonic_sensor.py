# Catalog No. 2760342
# https://www.radioshack.com/collections/sensors/products/radioshack-ultrasonic-range-sensor

import time
import requests
import RPi.GPIO as GPIO
from uuid import getnode as get_mac
#from DataUtil import DataUtil
#from SensorData import SensorData
#from coimport SensorData
#from common import DataUtil
from termcolor import colored

RANGE_FINDER_PIN = 11 # SCLK
LED_PIN = 12
ENDPOINT = 'http://xx.xx.xxx.xx:9090/sensordata/save'

GPIO.setmode(GPIO.BCM)
GPIO.setup(LED_PIN,GPIO.OUT)
#sensor = SensorData("UltraSonic Sensor")
#dUtil = DataUtil()

def getMACaddress():
    mac = get_mac()
    macString = ':'.join(("%012X" % mac)[i:i + 2] for i in range(0, 12, 2))
    return macString

macString = getMACaddress()

while True:
    GPIO.output(LED_PIN, GPIO.LOW)

    GPIO.setup(RANGE_FINDER_PIN, GPIO.OUT)
    GPIO.output(RANGE_FINDER_PIN,False)
    # signal hardware.
    time.sleep(0.0001)
    GPIO.output(RANGE_FINDER_PIN,True)
    time.sleep(0.0001)
    GPIO.output(RANGE_FINDER_PIN,False)

    # wait for ultrasonic reply.
    GPIO.setup(RANGE_FINDER_PIN, GPIO.IN,pull_up_down=GPIO.PUD_UP)
    while GPIO.input(RANGE_FINDER_PIN)==0:
    start_time = time.time()
    while GPIO.input(RANGE_FINDER_PIN)==1:
        end_time = time.time()
            
    # d = v*t (distance traveled is to/from object, therefore divide by 2)
    distance = (34000 * (end_time-start_time)) / 2
    print 'Distance: {0:.4f}'.format(distance)
    if distance < 10:
        headers ={'Content-type' : 'text/plain'}
        Str = '{\"deviceId\": \"' + str(getMACaddress()) + '\",\n"data\": {\n"ultraSonicSensor\" : ' + str(distance) + '\n} \n}"'
        print ('Sensor data JSON \n'+ Str)
        r = requests.post(url = ENDPOINT, data = Str, headers = headers)
        print(r.text)
        GPIO.output(LED_PIN, GPIO.HIGH)
        print colored('Proximity Alert!', 'red')
            #sensor.addValue(distance)
            #print('Sensor data JSON ' + dUtil.toJsonFromSensorData(sensor)) #send the return from this to cloud end point
            
        time.sleep(1)

