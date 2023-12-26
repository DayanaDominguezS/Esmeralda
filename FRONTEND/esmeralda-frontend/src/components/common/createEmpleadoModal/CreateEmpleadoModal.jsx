import { Box, Modal, TextField} from '@mui/material'
import styleEmpleado from "../createEmpleadoModal/Create.empleado.css"
const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    bgcolor: 'background.paper',
    border: '2px solid #000',
    boxShadow: 24,
    p: 4,
  };

const CreateEmpleadoModal = ({open, handleClose}) => {
  
    return (
      <div>
        <Modal
          open={open}
          onClose={handleClose}
          aria-labelledby="modal-modal-title"
          aria-describedby="modal-modal-description"
        >
          <Box sx={style}>
            <form className={styleEmpleado.createEmpleado}>
                <TextField id="outlined-basic" label="Outlined" variant="outlined" fullWidth/>
                <TextField id="outlined-basic" label="Outlined" variant="outlined" fullWidth/>
                <TextField id="outlined-basic" label="Outlined" variant="outlined" fullWidth/>
                <TextField id="outlined-basic" label="Outlined" variant="outlined" fullWidth/>
            </form>
          </Box>
        </Modal>
      </div>
    );
}

export default CreateEmpleadoModal
