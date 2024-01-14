import React from "react";
import {
  TextField,
  FormControl,
  FormLabel,
  RadioGroup,
  FormControlLabel,
  Radio,
  Box,
  InputAdornment,
  Select,
  MenuItem,
  InputLabel,
} from "@mui/material";

const CharacterForm = ({
  characterName,
  onCharacterNameChange,
  characterAlignment,
  setCharacterAlignment,
  characterRace,
  setCharacterRace,
  availableAlignments,
  availableRaces,
  availableAbilities,
  abilityValues,
  handleAbilityChange,
  abilityErrors,
  diceRolls,
  calculateRollUsages,
}) => {
  return (
    <div>
      <div>
        <label htmlFor="characterName">Character Name</label>
        <TextField
          value={characterName}
          onChange={onCharacterNameChange}
          required
          type="text"
          name="characterName"
          id="characterName"
          sx={{ m: 1, width: "25ch" }}
        />
      </div>

      <FormControl sx={{ m: 1, width: "25ch" }}>
        {/* Alignment Selection */}
        <FormLabel id="alignment-selection-group-label">Alignment</FormLabel>
        <RadioGroup
          aria-labelledby="alignment-selection-group-label"
          defaultValue="NEUTRAL"
          name="radio-buttons-group"
          value={characterAlignment}
          onChange={(e) => setCharacterAlignment(e.target.value)}
        >
          {availableAlignments.map((alignment) => (
            <FormControlLabel
              key={alignment.name}
              value={alignment.name}
              control={<Radio />}
              label={alignment.name.replace(/_/g, " ").toLowerCase()}
            />
          ))}
        </RadioGroup>
      </FormControl>

      <FormControl sx={{ m: 1, width: "25ch" }}>
        {/* Race Selection */}
        <FormLabel id="race-selection-group-label">Race</FormLabel>
        <RadioGroup
          aria-labelledby="race-selection-group-label"
          defaultValue="HUMAN"
          name="radio-buttons-group"
          value={characterRace}
          onChange={(e) => setCharacterRace(e.target.value)}
        >
          {availableRaces.map((race) => (
            <FormControlLabel
              key={race.name}
              value={race.name}
              control={<Radio />}
              label={race.name.replace(/_/g, " ").toLowerCase()}
            />
          ))}
        </RadioGroup>
      </FormControl>

      {availableAbilities.map((ability) => (
        <Box
          key={ability.name}
          sx={{ display: "flex", flexWrap: "wrap", m: 1 }}
        >
          <FormControl variant="standard" sx={{ m: 1, minWidth: 120 }}>
            <InputLabel id={`${ability.name}-label`}>{ability.name}</InputLabel>
            <Select
              labelId={`${ability.name}-label`}
              id={`${ability.name}-select`}
              value={abilityValues[ability.name] || ""}
              onChange={(e) =>
                handleAbilityChange(ability.name, e.target.value)
              }
              label={ability.name}
              error={!!abilityErrors[ability.name]}
            >
              <MenuItem value="">
                <em>None</em>
              </MenuItem>
              {diceRolls.map((roll, index) => {
                const rollUsage = calculateRollUsages(); //here
                const isDisabled = rollUsage[roll] <= 0;
                return (
                  <MenuItem key={index} value={roll} disabled={isDisabled}>
                    {roll}
                  </MenuItem>
                );
              })}
            </Select>
          </FormControl>
        </Box>
      ))}
    </div>
  );
};

export default CharacterForm;
