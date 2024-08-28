load into table EVENT_E_HANDOVER
(
    EVENT_ID,
    START_RNC,
    END_RNC,
    START_C_ID,
    END_C_ID,
    IMSI,
    DATETIME_ID
)
from '/eniq/home/dcuser/exuexie/load/HANDOVER.bin'
quotes off
escapes off
format 'binary'
