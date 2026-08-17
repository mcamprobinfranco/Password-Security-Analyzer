import { useState } from 'react';

function PasswordInput({ onAnalyze }) {
  const [password, setPassword] = useState('');

  const handleChange = (e) => {
    const value = e.target.value;
    setPassword(value);
    onAnalyze(value);
  };

  return (
    <>
      <label htmlFor="password">Contraseña</label>
      <input
        id="password"
        type="text"
        value={password}
        onChange={handleChange}
        placeholder="Escribe tu contraseña..."
      />
    </>
  );
}

export default PasswordInput;