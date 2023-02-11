import { BrowserRouter, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import LoginPage from "./pages/Auth/LoginPage";
import PatientPage from "./pages/PatientPage";
import RegisterPage from "./pages/Auth/RegisterPage";
import AddPatient from "./pages/AddPatient";
import PatientDetails from "./pages/PatientDetails";
import UpdatePatient from "./pages/UpdatePatient";
import AddMedicine from "./pages/AddMedicine";
import MedicineList from "./pages/MedicineList";
import MedicineDetails from "./pages/MedicineDetails";
import DoctorProfile from "./pages/DoctorProfile";
import UpdateMedicine from "./pages/UpdateMedicine";
import Footer from "./components/Footer";

import AddDocter from "./pages/AddDocter";
import AddClinic from "./pages/AddClinic";
import ClinicPage from "./pages/ClinicPage";
import DoctorPage from "./pages/DoctorPage";
import ClinicDetails from "./pages/ClinicDetails";
import DoctorDetails from "./pages/DoctorsDetails";
import UpdateClinic from "./pages/UpdateClinic";
import UpdateDoctor from "./pages/UpdateDoctor";


function App() {
  return (

    <BrowserRouter>
    <Navbar />
      <Routes>
          <Route index element={<ClinicPage />} />
          <Route path="/register" element={<RegisterPage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/add-patient" element={<AddPatient />} />
          <Route path="/patient-list" element={<PatientPage />} />
          <Route path="/patient-details/:patientId" element={<PatientDetails />} />
          <Route path="/update-patient/:patientId" element={<UpdatePatient />} />
          <Route path="/add-medicine/:patientId" element={<AddMedicine />} />
          <Route path="/medicine-list/:patientId" element={<MedicineList />} />
          <Route path="/medicine-details/:medicineId" element={<MedicineDetails />} />
          <Route path="/update-medicine/:medicineId" element={<UpdateMedicine />} />
          <Route path="/doctor-profile/:doctorId" element={<DoctorProfile />} />

          <Route path="/add-clinic" element={<AddClinic/>} />
          <Route path="/clinic-list" element={<ClinicPage/>} />
          <Route path="/clinic-details/:clinicId" element={<ClinicDetails />} />
          <Route path="/update-clinic/:clinicId" element={<UpdateClinic />} />

          <Route path="/add-doctor/:clinicId" element={<AddDocter />} />
          <Route path="/doctor-list/:clinicId" element={<DoctorPage/>} />
          <Route path="/doctor-details/:doctorId" element={<DoctorDetails />} />
          <Route path="/update-doctor/:doctorId" element={<UpdateDoctor />} />
      </Routes>
      <Footer/>
    </BrowserRouter>
  );
}

export default App;