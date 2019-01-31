import React, {Component} from 'react'
import DefaultPage from '../DefaultPage'
import {Row, Col} from 'reactstrap'
import {DefaultForm as Form} from '../../common/Form'
import Button from '../../common/Button'

class FormPage extends Component{
    render(){
        let customButtons = this.props.customButtons;

        if(!customButtons){
            customButtons = (
                <Row>
                    <Col md="12">
                        <Button type="submit" 
                                color="default" 
                                icon={this.props.submitIcon || 'save'} 
                                label={this.props.submitLabel || 'Salvar'} />
                        <Button color="primary" 
                                icon="mail-reply"
                                label="Voltar"
                                onClick={this.props.voltar}/>
                    </Col>
                </Row>                
            ) 
        }

        return(
            <DefaultPage {...this.props}>
                <Form onSubmit={this.props.onSubmit}>
                    {this.props.children}
                    {customButtons}
                </Form>
            </DefaultPage>
        )
    }
}

export default FormPage