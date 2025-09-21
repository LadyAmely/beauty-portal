// Button.stories.jsx
import Button from './Button';

export default {
    title: 'Components/Button',
    component: Button,
};

export const Primary = () => <Button variant="primary">Primary</Button>;
export const Secondary = () => <Button variant="secondary">Secondary</Button>;
export const Disabled = () => <Button variant="primary" disabled>Disabled</Button>;
