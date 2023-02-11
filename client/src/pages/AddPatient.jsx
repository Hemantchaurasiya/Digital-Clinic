import React, { useState } from 'react'
import { Grid, GridItem, FormControl, Input, FormLabel, Select, Badge, Button, Heading, Text, Center } from '@chakra-ui/react';
import { registerPatient } from '../http/api';

export default function AddPatient() {
    const [firstname, setFirstname] = useState("");
    const [lastname, setLastname] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [phone, setPhone] = useState("");
    const [isLoading, setIsLoading] = useState(false);
    const [isdone, setIsDone] = useState(false);

    const handleSubmit = async (e) => {
        e.preventDefault();
        const patient = {
            firstname,
            lastname,
            email,
            password,
            phone,
        }
        setIsLoading(true);
        try {
            const data = await registerPatient(patient);
            setIsDone("User Register Sccessfully!");
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
                        create New Patient
                    </Badge>
                </Text>
                <div style={{ backgroundColor: "white", padding: "20px" }}>
                    <form onSubmit={handleSubmit}>
                        <FormControl>
                            <FormLabel>First name</FormLabel>
                            <Input
                                onChange={event => setFirstname(event.currentTarget.value)}
                                placeholder='First name' />

                            <FormLabel>Last name</FormLabel>
                            <Input
                                onChange={event => setLastname(event.currentTarget.value)}
                                placeholder='First name' />

                            <FormLabel>Email</FormLabel>
                            <Input
                                onChange={event => setEmail(event.currentTarget.value)}
                                placeholder='Email' />

                            <FormLabel>Password</FormLabel>
                            <Input
                                onChange={event => setPassword(event.currentTarget.value)}
                                placeholder='Password' />

                            <FormLabel>Phone</FormLabel>
                            <Input
                                onChange={event => setPhone(event.currentTarget.value)}
                                placeholder='Phone' />

                            <Button type='submit' mt={5} size='lg' colorScheme='green'>Submit</Button>
                            <Text mb={4} fontSize='xl' fontWeight='bold'>
                                <Badge mr={1} ml='1' fontSize='1.0em' colorScheme='green'>
                                    {isdone}
                                </Badge>
                            </Text>
                        </FormControl>
                    </form>
                </div>
            </GridItem>
        </Grid>
    )
};