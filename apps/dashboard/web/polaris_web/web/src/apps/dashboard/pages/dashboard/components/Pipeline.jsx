import { Card, DataTable, Scrollable, Text,HorizontalStack , VerticalStack,Modal,Button } from '@shopify/polaris'
import React , {useState} from 'react'
import transform from '../transform'
import { useNavigate } from "react-router-dom"


function Pipeline({riskScoreMap, collections, collectionsMap}) {

    const [active, setActive] = useState(false);

    const handleShowModal = () => {
        setActive(true);
    };

    const navigate = useNavigate();

    function CicdModal({ active, setActive }) {

        
    
        const primaryAction = () => {
            navigate('/dashboard/settings/integrations/ci-cd');
        };
    
        const secondaryAction = () => {
            setActive(false);
        };
    
        return (
            <Modal
                key="modal"
                open={active}
                onClose={() => setActive(false)}
                title="Add to CI/CD pipeline"
                primaryAction={{
                    id: "add-ci-cd",
                    content: 'Create Token',
                    onAction: primaryAction
                }}
                secondaryActions={{
                    id: "close-ci-cd",
                    content: 'Cancel',
                    onAction: secondaryAction
                }}
            >
                <Modal.Section>
                    <VerticalStack gap={2}>
                        <HorizontalStack gap={2} align="start">
                            <Text>
                                Lorem ipsum dolor sit amet consectetur, adipisicing elit. Fugiat explicabo, quidem earum ratione quaerat deleniti nostrum velit sequi pariatur obcaecati id &nbsp;
                                <a href="https://docs.akto.io/api-security-testing/how-to/setup-github-integration-for-ci-cd" target="_blank" rel="noopener noreferrer" style={{ color: "#3385ff", textDecoration: 'none' }}>
                                    Learn More
                                </a>
                            </Text>
                        </HorizontalStack>
                    </VerticalStack>
                </Modal.Section>
            </Modal>
        );
    }



    const tableRows = transform.prepareTableData(riskScoreMap,collections, collectionsMap);

    return (
        <Card>
            <VerticalStack gap={5}>
                <VerticalStack gap={2}>
                    <Text variant="bodyLg" fontWeight="semibold">Add in your CI/CD pipeline</Text>
                    <Text>Seamlessly enhance your web application security with CI/CD integration, empowering you to efficiently detect vulnerabilities, analyze and intercept web traffic, and fortify your digital defenses.</Text>
                </VerticalStack>
                <Scrollable style={{maxHeight: '200px', paddingBottom:'10px'}} shadow>
                    <DataTable headings={[]}
                        columnContentTypes={[
                            'text',
                            'numeric'
                        ]}
                        rows={tableRows}
                        increasedTableDensity
                        truncate
                    /> 
                </Scrollable>
                { active && (
    <CicdModal
        active={active}
        setActive={setActive}
    />
)}
                <VerticalStack gap={5}>

                <HorizontalStack gap={4}>
                <Button onClick={handleShowModal} > Create Token</Button> 
                <span style={{color:"#3385ff"}} > Learn More</span>
                </HorizontalStack>
                </VerticalStack>
            </VerticalStack>
        </Card>
    )
}

export default Pipeline