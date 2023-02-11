import React from 'react';
import { Wrap, WrapItem, Avatar, Button, ButtonGroup } from '@chakra-ui/react';
import "../styles/navbar.css";
import { Link } from 'react-router-dom';

function Navbar() {
  let doctorId = 1;

  return (
    <div className='navbar'>
      <Wrap >
        <WrapItem>
          <Avatar ml={4} src='https://bit.ly/dan-abramov' />
          <ButtonGroup ml={6} mt={1} gap='4'>
            <Button colorScheme='blackAlpha'>
              <Link to={`/doctor-profile/${doctorId}`}>Profile</Link>
            </Button>
            <Button colorScheme='blackAlpha'>
              <Link to={"/add-clinic"}>Add Clinic</Link>
            </Button>
          </ButtonGroup>
        </WrapItem>
      </Wrap>
    </div>
  )
}

export default Navbar;

