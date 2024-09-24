import { Modal, Text, TextField, VerticalStack } from '@shopify/polaris'
import React, { useState } from 'react'
import func from '@/util/func'
import homeRequests from "../pages/home/api"

const WelcomeBackDetailsModal = ({ isAdmin }) => {
    const [modalToggle, setModalToggle] = useState(window.SHOULD_ASK_WELCOME_DETAILS === 'true')
    
    const hasUsername = window.USER_FULL_NAME !== window.USER_NAME

    const [username, setUsername] = useState(hasUsername ? window.USER_FULL_NAME : "")
    const [organization, setOrganization] = useState("")

    const handleWelcomeBackDetails = async () => {
        if(!isAdmin && organization.length > 0) return

        const nameReg = /^[\w\s-]{1,}$/;
        const orgReg = /^[\w\s.&-]{1,}$/;
        if(!nameReg.test(username.trim()) || (isAdmin && !orgReg.test(organization.trim()))) {
            func.setToast(true, true, "Please enter valid details.")
            return
        }

        const email = window.USER_NAME

        const isUpdated = await homeRequests.updateUsernameAndOrganization(email ,username, organization)

        if(isUpdated) {
            setModalToggle(false)
        }
    }

    return (
        <Modal
            size="small"
            open={modalToggle}
            title={<div className='welcome-back-modal-title'>Welcome back</div>}
            primaryAction={{
                content: 'Save',
                onAction: () => handleWelcomeBackDetails()
            }}
        >
            <Modal.Section>
                <VerticalStack gap={4}>
                    <Text variant="bodyMd" color="subdued">
                        Tell us more about yourself.
                    </Text>

                    <TextField
                        label="Name"
                        value={username}
                        disabled={hasUsername}
                        placeholder="Joe doe"
                        onChange={setUsername}
                        autoComplete="off"
                    />

                    {
                        isAdmin && <TextField
                            label="Organization name"
                            disabled={!isAdmin}
                            value={organization}
                            placeholder="Acme Inc"
                            onChange={setOrganization}
                            autoComplete="off"
                        />
                    }
                </VerticalStack>
            </Modal.Section>
        </Modal>
    )
}

export default WelcomeBackDetailsModal