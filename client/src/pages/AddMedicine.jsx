import React, { useState, useEffect } from 'react'
import { Grid, GridItem, FormControl, Input, FormLabel, Textarea, Badge, Button, Heading, Text, Center } from '@chakra-ui/react';
import { createMedicine, getPatientById } from '../http/api';
import { useParams } from 'react-router-dom';

export default function AddPatient() {
    let patientId = useParams().patientId;
    const [patient, setPatient] = useState({});

    useEffect(() => {
        const fetchPatient = async () => {
            const data = await getPatientById(patientId);
            console.log(data);
            setPatient(data);
        }
        fetchPatient();
    }, [patientId]);


    const [name, setName] = useState("");
    const [disease, setDisease] = useState("");
    const [description, setDescription] = useState("");
    const [isLoading, setIsLoading] = useState(false);
    const [isdone, setIsDone] = useState(false);

    const handleSubmit = async (e) => {
        e.preventDefault();
        const medicine = {
            name,
            disease,
            description,
            date: new Date(),
        }
        setIsLoading(true);
        try {
            console.log(medicine);
            const data = await createMedicine(patientId, medicine);
            setIsDone("Medicine Register Sccessfully!");
            setIsLoading(false);
        } catch (error) {
            console.log(error);
            setIsLoading(false);
        }
    }

    return (
        <Grid className='homeContainer' h='100vh'>
            <GridItem ml={20} marginRight={20} colSpan={5}>
                <Text mb={4} fontSize='xl' fontWeight='bold'>
                    <Badge mr={1} ml='1' fontSize='1.0em' colorScheme='green'>
                        create New Medicine
                    </Badge>
                </Text>
                <Text mb={4} fontSize='xl' fontWeight='bold'>
                    <Badge mr={1} ml='1' fontSize='1.0em' colorScheme='green'>
                        Patient Name : {patient.firstname + " " + patient.lastname}
                    </Badge>
                </Text>
                <div style={{ backgroundColor: "white", padding: "20px" }}>
                    <form onSubmit={handleSubmit}>
                        <FormControl>
                            <FormLabel>Medicine name</FormLabel>
                            <Input
                                onChange={event => setName(event.currentTarget.value)}
                                placeholder='First name' />
                        </FormControl>
                        <FormControl>
                            <FormLabel>Disease name</FormLabel>
                            <Input
                                onChange={event => setDisease(event.currentTarget.value)}
                                placeholder='First name' />
                        </FormControl>

                        <FormControl>
                            <FormLabel>Description</FormLabel>
                            <Textarea
                                onChange={event => setDescription(event.currentTarget.value)}
                                placeholder='Enter Medicine Description' />
                        </FormControl>


                        <Button type='submit' mt={5} size='lg' colorScheme='green'>Submit</Button>
                        <Text mb={4} fontSize='xl' fontWeight='bold'>
                            <Badge mr={1} ml='1' fontSize='1.0em' colorScheme='green'>
                                {isdone}
                            </Badge>
                        </Text>
                    </form>
                </div>
            </GridItem>
        </Grid>
    )
};