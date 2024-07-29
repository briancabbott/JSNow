
export interface IElement {}

export interface IElementIdentity {}

export class Element implements IElement {
    elementIdentity
}

export class EmbeddedElement extends Element {}