import { Avatar, Box, InlineStack, Text, BlockStack } from '@shopify/polaris'
import React from 'react'

function ActivityTracker({ latestActivity }) {
    const formatDate = (epochTime) => {
        const date = new Date(epochTime * 1000)
        const formattedDate = date.toLocaleDateString('en-US', { 
            year: 'numeric', month: 'short', day: 'numeric' 
        })
        const formattedTime = date.toLocaleTimeString('en-US', { 
            hour: 'numeric', minute: '2-digit', hour12: true 
        })
        return [formattedDate, formattedTime]
    }
    
    // Group events by date
    const groupEventsByDate = (events) => {
        return events.reduce((groupedEvents, event) => {
            const eventDate = formatDate(event.timestamp)[0]
            if (!groupedEvents[eventDate]) {
                groupedEvents[eventDate] = []
            }
            groupedEvents[eventDate].push(event)
            return groupedEvents
        }, {})
    }

    const groupedActivity = groupEventsByDate(latestActivity)

    return (
        <Box padding={500}>
            <BlockStack>
                {Object.keys(groupedActivity).map((date, dateIndex) => (
                    <Box key={dateIndex}>
                        <InlineStack gap={400}>
                            <Box borderColor='border-secondary' borderInlineEndWidth='2' width='0' paddingInlineStart={300} minHeight='44px' />
                            <Text variant="bodySm" tone="subdued" style={{ marginBottom: '10px' }}>
                                {date}
                            </Text>
                        </InlineStack>
                        {groupedActivity[date].map((event, eventIndex) => (
                            <InlineStack key={eventIndex} align='space-between'>
                                <InlineStack gap={300}>
                                    <Box>
                                        <div style={{marginBlock: '5px'}}><Avatar shape="round" size="xs" source="/public/issues-event-icon.svg" /></div>
                                        {eventIndex < (groupedActivity[date].length - 1) ? (
                                            <Box borderColor='border-secondary' borderInlineEndWidth='2' width='0' paddingInlineStart={300} minHeight='12px' />
                                        ) : null}
                                    </Box>
                                    <Box>
                                        <div style={{marginBlock: '5px'}}><Text variant="bodyMd">{event.description}</Text></div>
                                    </Box>
                                </InlineStack>
                                <div style={{marginBlock: '5px'}}>
                                    <Text variant="bodySm" tone="subdued">
                                        {formatDate(event.timestamp)[1]}
                                    </Text>
                                </div>
                            </InlineStack>
                        ))}
                    </Box>
                ))}
            </BlockStack>
        </Box>
    );
}

export default ActivityTracker;
