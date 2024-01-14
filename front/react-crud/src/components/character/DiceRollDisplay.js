import React from "react";
import TextField from "@mui/material/TextField";
import Box from "@mui/material/Box";

const DiceRollsDisplay = ({ diceRolls }) => {
  return (
    <Box sx={{ m: 1 }}>
      {diceRolls.map((roll, index) => (
        <TextField
          key={index}
          label={`Roll ${index + 1}`}
          value={roll}
          InputProps={{
            readOnly: true,
          }}
          sx={{ m: 1, width: "25ch" }}
        />
      ))}
    </Box>
  );
};

export default DiceRollsDisplay;
