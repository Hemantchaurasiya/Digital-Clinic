import React, { useEffect, useState } from 'react';
import {
    Grid, GridItem, Box, Flex, Spacer, Input, Button,
    Menu, MenuButton, MenuList, MenuItem, Wrap, WrapItem, ButtonGroup
} from '@chakra-ui/react';
import "../styles/home.css";
import { getAllClinic, searchClinic } from '../http/api';
import { Previous, Paginator, Next, } from 'chakra-paginator';
import { CgChevronLeft, CgChevronRight } from 'react-icons/cg';
import ClinicList from '../components/ClinicList';

export default function ClinicPage() {
    const [clinics, setClinics] = useState([]);
    const [search, setSearch] = useState("");

    const itemLimit = 10;
    const [pagesQuantity, setPagesQuantity] = useState(1);
    const [curPage, setCurPage] = useState(0);

    const normalStyles = {
        bg: 'white'
    };

    const activeStyles = {
        bg: 'blue.300'
    };

    const handlePageChange = (page) => {
        setCurPage(page - 1);
    };

    useEffect(() => {
        const pagesTotal = Math.ceil(clinics.length / itemLimit) + 1;
        setPagesQuantity(pagesTotal);
    }, [clinics.length]);

    useEffect(() => {
        const fetchClinic = async () => {
            const data = await getAllClinic();
            setClinics(data);
        }
        fetchClinic();
    }, []);

    const submitHandle = async (e) => {
        e.preventDefault();
        try {
            console.log(search);
            const data = await searchClinic(search);
            console.log(data);
            setClinics(data);
        } catch (error) {
            console.log(error);
        }
    }

    return (
        <Grid className='homeContainer' h='100%'>
            <GridItem ml={20} marginRight={20} colSpan={5}>

                <form onSubmit={submitHandle}>
                    <Wrap >
                        <WrapItem>
                            <Input
                                onChange={event => setSearch(event.currentTarget.value)}
                                placeholder='Search...' ml={3} mt={1} />
                            <ButtonGroup ml={3} mt={1} gap='4'>
                                <Button type='submit' colorScheme='blackAlpha'>
                                    Search Clinic
                                </Button>
                            </ButtonGroup>
                        </WrapItem>
                    </Wrap>
                </form>

                <Box
                    w='100%'
                    mt={5}
                    className='rightSection'>
                    <ClinicList clinics={clinics} curPage={curPage} clinicLimit={itemLimit} />

                    <Flex p={2}>
                        <Spacer />
                        <Paginator
                            onPageChange={handlePageChange}
                            pagesQuantity={pagesQuantity - 1}>
                            <Previous mr={5} bg="green">
                                <CgChevronLeft />
                            </Previous>
                            <Next bg="green">
                                <CgChevronRight />
                            </Next>
                        </Paginator>
                    </Flex>
                </Box>
            </GridItem>
        </Grid>
    )
};