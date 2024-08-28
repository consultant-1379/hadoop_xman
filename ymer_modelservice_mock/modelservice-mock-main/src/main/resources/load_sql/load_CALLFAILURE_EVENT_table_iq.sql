load into table EVENT_E_CALLFAILURE
(
    EVENT_ID,
    CAUSE_VALUE,
    EXTENDED_CAUSE_VALUE,
    IMSI,
    DATETIME_ID
)
from '/eniq/home/dcuser/exuexie/load/CALLFAILURE.bin'
quotes off
escapes off
byte order LOW
format 'binary'
