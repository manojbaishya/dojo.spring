import { Temporal } from '@js-temporal/polyfill';
import { createLogger, format, transports } from 'winston';

const { combine, timestamp } = format;
const { Now } = Temporal;

const DEFAULT_LOG_LEVEL = 'info';

function getTemporalISOString() {
    const localZoned = Now.plainDateTimeISO();
    return localZoned.toString({ fractionalSecondDigits: 3 });
}

const logger = createLogger({
    level: DEFAULT_LOG_LEVEL,

    format: combine(
        timestamp({
            format: getTemporalISOString
        }),
        format.printf(info => {
            return `${info.timestamp} [${info.level.toUpperCase()}]: ${info.message}`;
        })
    ),
    transports: [
        new transports.Console({
            level: 'debug'
        })
    ]
});

export default logger;
